package com.learn.spr

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController


@RestController
@SpringBootApplication
class PostController(val repository: PostRepository) {
    @GetMapping("/posts")
    fun getPosts(): List<Post> = repository.findAll()

    @GetMapping("/posts/{postId}")
    fun getPost(@PathVariable postId: Int): Post {
        return repository.findById(postId).orElseThrow { Exception("Post not found") }
    }
}


@Configuration
internal class LoadDatabase {
    @Bean
    fun initDatabase(repository: PostRepository): CommandLineRunner {
        return CommandLineRunner { _: Array<String?>? ->
            log.info("preloading database...")
            for (count in 0..10) {
                repository.save(Post("Post #$count"))
            }
            log.info("preloading database finished...")
        }
    }

    companion object {
        private val log = LoggerFactory.getLogger(LoadDatabase::class.java)
    }
}

