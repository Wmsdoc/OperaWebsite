//获取音频下载排行
export const getAudioDownloadRank = (time: string) => {
	return http.get(`/opera/audio/getDownloadRank`, {
		params: {
			time,
		},
	})
}
//获取音频评论排行
export const getAudioCommentRank = (time: string) => {
	return http.get(`/opera/audio/getCommentRank`, {
		params: {
			time,
		},
	})
}

//获取视频下载排行
export const getVideoDownloadRank = (time: string) => {
	return http.get(`/opera/video/getDownloadRank`, {
		params: {
			time,
		},
	})
}
//获取视频评论排行
export const getVideoCommentRank = (time: string) => {
	return http.get(`/opera/video/getCommentRank`, {
		params: {
			time,
		},
	})
}
//新增视频下载量
export const addVideoDownloadNum = (videoId: string) => {
	return http.post(`/opera/video/addDownloadNum/` + videoId)
}
//新增音频下载量
export const addAudioDownloadNum = (audioId: string) => {
	return http.post(`/opera/audio/addDownloadNum/` + audioId)
}
