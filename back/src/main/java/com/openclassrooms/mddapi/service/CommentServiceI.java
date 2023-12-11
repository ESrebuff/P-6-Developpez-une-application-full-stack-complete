package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.CommentRequestDto;
import com.openclassrooms.mddapi.dto.CommentResponseDto;

public interface CommentServiceI {
    
    CommentResponseDto create(CommentRequestDto commentRequestDto);

}
