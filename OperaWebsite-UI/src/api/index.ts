// 查询戏曲音频
export function getAudio(queryParams: any) {
	return http.get('/opera/audio/getByPageAndParams', {
		params: {
			typeId: queryParams.typeId,
			timeFlag: queryParams.timeFlag,
			filename: queryParams.filename,
			pageNum: queryParams.pageNumAudio,
			pageSize: queryParams.pageSizeAudio,
		},
	})
}
// 根据Id查询戏曲音频详情
export function getAudioDetails(audioId: any) {
	return http.get('/opera/audio/getById', {
		params: {
			audioId: audioId,
		},
	})
}
// 查询戏曲视频
export function getVideo(queryParams: any) {
	return http.get('/opera/video/getByPageAndParams', {
		params: {
			typeId: queryParams.typeId,
			timeFlag: queryParams.timeFlag,
			filename: queryParams.filename,
			pageNum: queryParams.pageNumVideo,
			pageSize: queryParams.pageSizeVideo,
		},
	})
}
// 根据Id查询戏曲音频详情
export function getVideoDetails(videoId: any) {
	return http.get('/opera/video/getById', {
		params: {
			videoId: videoId,
		},
	})
}
