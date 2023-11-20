package com.opera.opera.service.impl;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.opera.opera.mapper.OperaVideoCommentMapper;
import com.opera.opera.domain.OperaVideoComment;
import com.opera.opera.service.OperaVideoCommentService;
@Service
public class OperaVideoCommentServiceImpl extends ServiceImpl<OperaVideoCommentMapper, OperaVideoComment> implements OperaVideoCommentService{

}
