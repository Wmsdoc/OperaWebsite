package com.opera.opera.service.impl;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.opera.opera.domain.OperaAudio;
import com.opera.opera.mapper.OperaAudioMapper;
import com.opera.opera.service.OperaAudioService;
@Service
public class OperaAudioServiceImpl extends ServiceImpl<OperaAudioMapper, OperaAudio> implements OperaAudioService{

}
