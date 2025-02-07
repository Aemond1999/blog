package com.hya.config;

import com.hya.common.bo.ViewCountsBo;
import com.hya.constants.RedisPrefixConstants;
import com.hya.mapper.ArticleMapper;
import com.hya.service.ArticleBodyService;
import com.hya.service.TagService;
import com.hya.service.UserService;
import jakarta.annotation.Resource;
import lombok.EqualsAndHashCode;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
@EnableScheduling
public class ScheduleTask {
    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 定时更新评论数
     */
    @Scheduled(cron = "0 */10 * * * ?")
    private void updateViewCounts() {
        Set<String> keys = stringRedisTemplate.keys(RedisPrefixConstants.VIEW_COUNTS_KEY_PREFIX + "*");
        List<ViewCountsBo> list = new ArrayList<>();
        for (String key : keys) {
            String viewCounts = stringRedisTemplate.opsForValue().get(key);
            String articleId = key.substring(RedisPrefixConstants.VIEW_COUNTS_KEY_PREFIX.length());
            ViewCountsBo viewCountsBo = new ViewCountsBo(Long.parseLong(articleId), Integer.parseInt(viewCounts));
            list.add(viewCountsBo);
        }
        System.out.println(list);
        if (!list.isEmpty()) {
            articleMapper.updateBathViewCountsById(list);
        }
    }
}
