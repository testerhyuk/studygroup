package hyuk.view.controller;

import hyuk.view.service.ArticleViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ArticleViewController {
    private final ArticleViewService articleViewService;

    @PostMapping("/v1/article-views/recruitments/{recruitmentId}/users/{userId}")
    public Long increase(@PathVariable("recruitmentId") Long recruitmentId, @PathVariable("userId") Long userId) {
        return articleViewService.increase(recruitmentId, userId);
    }

    @GetMapping("/v1/article-views/recruitments/{recruitmentId}/count")
    public Long count(@PathVariable("recruitmentId") Long recruitmentId) {
        return articleViewService.count(recruitmentId);
    }
}
