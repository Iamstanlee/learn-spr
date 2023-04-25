package com.learn.spr

import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Int>