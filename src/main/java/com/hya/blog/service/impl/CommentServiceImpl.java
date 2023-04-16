package com.hya.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hya.blog.enums.HttpCodeEnum;
import com.hya.blog.mapper.CommentMapper;
import com.hya.blog.pojo.Article;
import com.hya.blog.pojo.Comment;
import com.hya.blog.pojo.User;
import com.hya.blog.service.CommentService;
import com.hya.blog.service.UserService;
import com.hya.blog.utils.CopyBeanUtil;
import com.hya.blog.utils.Result;
import com.hya.blog.vo.CommentListVO;
import com.hya.blog.vo.PageVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Autowired
    UserService userService;
    @Autowired
    CommentService commentService;

    //获取文章下的评论评论
    @Override
    public Result commentList(Long current, Long size, Long id) {
        IPage<Comment> page = new Page<>(current, size);
        //查询根评论
        LambdaQueryWrapper<Comment> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Comment::getArticleId, id).eq(Comment::getRootId, -1);
        List<CommentListVO> oldCommentListVOs = CopyBeanUtil.copyBeanList(commentService.page(page, lqw).getRecords(), CommentListVO.class);
        List<CommentListVO> newCommentListVOs = oldCommentListVOs.stream()
                .map(s -> {
                    s.setUserName(userService.getUserById(s.getCreateBy()).getUsername());
                    //查询子评论
                    List<Comment> children = commentService.list(new LambdaQueryWrapper<Comment>().eq(Comment::getRootId, s.getId()));
                    List<CommentListVO> childCommentListVOs = CopyBeanUtil.copyBeanList(children, CommentListVO.class)
                            .stream().map(c -> {
                                c.setToCommentUserName(userService.getUserById(c.getToCommentUserId()).getUsername());
                                c.setUserName(userService.getUserById(c.getCreateBy()).getUsername());
                                return c;
                            }).collect(Collectors.toList());
                    s.setChildren(childCommentListVOs);
                    return s;
                }).collect(Collectors.toList());

        PageVO pageVO = new PageVO(newCommentListVOs, commentService.count(), current, size);
        return new Result(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg(), pageVO);
    }

    @Override
    public Result sendOrReplyComment(Comment comment) {
        boolean flag= commentService.save(comment);
       return new Result(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg(),flag);
    }


}
