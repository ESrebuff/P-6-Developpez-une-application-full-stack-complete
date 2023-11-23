package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.dto.CommentRequestDto;

public interface CommentServiceI {
    
    CommentDto create(CommentRequestDto commentRequestDto);

}
