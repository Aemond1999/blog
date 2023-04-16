package com.hya.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hya.blog.common.dto.CommentDTO;
import com.hya.blog.enums.HttpCodeEnum;
import com.hya.blog.mapper.CommentMapper;
import com.hya.blog.common.domain.CommentDO;
import com.hya.blog.service.CommentService;
import com.hya.blog.service.UserService;
import com.hya.blog.utils.CopyBeanUtil;
import com.hya.blog.utils.Result;
import com.hya.blog.common.vo.CommentVO;
import com.hya.blog.common.vo.PageVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, CommentDO> implements CommentService {
    @Autowired
    UserService userService;
    @Autowired
    CommentService commentService;

    //获取文章下的评论评论
    @Override
    public Result commentList(Long current, Long size, Long id) {
        IPage<CommentDO> page = new Page<>(current, size);
        //查询根评论
        LambdaQueryWrapper<CommentDO> lqw = new LambdaQueryWrapper<>();
        lqw.eq(CommentDO::getArticleId, id).eq(CommentDO::getRootId, -1);
        List<CommentVO> oldCommentVOS = CopyBeanUtil.copyBeanList(commentService.page(page, lqw).getRecords(), CommentVO.class);
        List<CommentVO> newCommentVOS = oldCommentVOS.stream()
                .map(s -> {
                    s.setUserName(userService.getUserById(s.getCreateBy()).getUsername());
                    //查询子评论
                    List<CommentDO> children = commentService.list(new LambdaQueryWrapper<CommentDO>().eq(CommentDO::getRootId, s.getId()));
                    List<CommentVO> childCommentVOS = CopyBeanUtil.copyBeanList(children, CommentVO.class)
                            .stream().map(c -> {
                                c.setToCommentUserName(userService.getUserById(c.getToCommentUserId()).getUsername());
                                c.setUserName(userService.getUserById(c.getCreateBy()).getUsername());
                                return c;
                            }).collect(Collectors.toList());
                    s.setChildren(childCommentVOS);
                    return s;
                }).collect(Collectors.toList());

        PageVO pageVO = new PageVO(newCommentVOS, commentService.count(), current, size);
        return new Result(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg(), pageVO);
    }

    @Override
    public Result sendOrReplyComment(CommentDTO commentDTO) {
        CommentDO commentDO = CopyBeanUtil.copyBean(commentDTO, CommentDO.class);
        boolean flag= commentService.save(commentDO);
       return new Result(HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg(),flag);
    }


}
