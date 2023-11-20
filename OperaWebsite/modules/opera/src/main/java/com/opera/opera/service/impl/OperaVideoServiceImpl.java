package com.opera.opera.service.impl;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.opera.opera.mapper.OperaVideoMapper;
import com.opera.opera.domain.OperaVideo;
import com.opera.opera.service.OperaVideoService;
@Service
public class OperaVideoServiceImpl extends ServiceImpl<OperaVideoMapper, OperaVideo> implements OperaVideoService{

}
