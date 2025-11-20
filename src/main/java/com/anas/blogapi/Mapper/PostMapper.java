package com.anas.blogapi.Mapper;

import com.anas.blogapi.Dto.Request.PostDto;
import com.anas.blogapi.Dto.Response.PostResDto;
import com.anas.blogapi.entities.Post;
import com.anas.blogapi.payloads.PageResponse;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;

@Mapper(componentModel = "spring")
public interface PostMapper {
    Post DtoReqToPost(PostDto postDto);
    PostResDto PostToResDto(Post post);

    static <T,U>PageResponse<U> map(Page<T> page, Function<T,U> mapper){
        List<U> PostDto = page.getContent().stream().map(mapper).toList();
        PageResponse<U> pageResponse = new PageResponse<>();
        pageResponse.setContent(PostDto);
        pageResponse.setPageNumber(page.getNumber());
        pageResponse.setPageSize(page.getSize());
        pageResponse.setTotalElements(page.getTotalElements());
        pageResponse.setTotalPages(page.getTotalPages());
        pageResponse.setLastPage(page.isLast());
        return pageResponse;

    }
}
