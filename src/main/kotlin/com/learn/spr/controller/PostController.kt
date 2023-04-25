package com.learn.spr.controller

import com.learn.spr.entity.Post
import com.learn.spr.repository.PostRepository
import com.learn.spr.util.ErrorResponse
import com.learn.spr.util.Response
import com.learn.spr.util.SuccessResponse
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping(consumes = ["application/json"])
class PostController(val repository: PostRepository) {

    @PutMapping("/posts")
    fun createPost(@RequestBody post: Post): Response<Post> {
        repository.save(post)
        return SuccessResponse(post)
    }

    @GetMapping("/posts")
    fun getPosts(): Response<List<Post>> = SuccessResponse(repository.findAll())

    @GetMapping("/posts/count")
    fun getPostsCount(): Response<Long> = SuccessResponse(repository.count())

    @GetMapping("/posts/{postId}")
    fun getPost(@PathVariable postId: Int): Response<Post> {
        val post = repository.findById(postId)
        return if (post.isPresent) {
            SuccessResponse(post.get())
        } else {
            ErrorResponse("Post not found")
        }
    }

    @DeleteMapping("/posts/{postId}/delete")
    fun deletePost(@PathVariable postId: Int): Response<String> {
        repository.deleteById(postId)
        return SuccessResponse("Post deleted")
    }
}


@Configuration
internal class LoadDatabase {
    @Bean
    fun initDatabase(repository: PostRepository): CommandLineRunner {
        return CommandLineRunner { _: Array<String?>? ->
            log.info("preloading database...")
            for (count in 0..9) {
                repository.save(Post("Post #$count"))
            }
            log.info("preloading database finished...")
        }
    }

    companion object {
        private val log = LoggerFactory.getLogger(LoadDatabase::class.java)
    }
}

