/**
 * 获取戏曲视频收藏量
 */
export const getVideoCollectionCount = (videoId: number) => {
	return http.get(`/opera/video/collection/count/${videoId}`)
}
/**
 * 获取戏曲音频收藏量
 */
export const getAudioCollectionCount = (audioId: number) => {
	return http.get(`/opera/audio/collection/count/${audioId}`)
}

/**
 * 获取戏曲音频评论
 */
export const getAudioComment = (audioId: number, page: number) => {
	return http.get(`/opera/audio/comment/getComment`, {
		params: {
			audioId,
			page,
		},
	})
}
/**
 * 获取戏曲视频评论
 */
export const getVideoComment = (videoId: number, page: number) => {
	return http.get(`/opera/video/comment/getComment`, {
		params: {
			videoId,
			page,
		},
	})
}
export const getAllType = () => {
	return http.get('/opera/type/getAllOperaType')
}

export const audioIsCollection = (audioId: number, playgoerId: any) => {
	return http.get('/opera/audio/collection/isCollection', {
		params: {
			audioId,
			playgoerId,
		},
	})
}
export const videoIsCollection = (videoId: number, playgoerId: any) => {
	return http.get('/opera/video/collection/isCollection', {
		params: {
			videoId,
			playgoerId,
		},
	})
}

export const deleteAudioCollection = (audioId: any, playgoerId: any) => {
	return http.delete('/opera/audio/collection/delete', {
		params: {
			audioId,
			playgoerId,
		},
	})
}
export const deleteVideoCollection = (videoId: number, playgoerId: any) => {
	return http.delete('/opera/video/collection/delete', {
		params: {
			videoId,
			playgoerId,
		},
	})
}
export const insertAudioCollection = (audioId: number, playgoerId: any) => {
	return http.post(
		'/opera/audio/collection/insert/' + audioId + '/' + playgoerId,
	)
}
export const insertVideoCollection = (videoId: number, playgoerId: any) => {
	return http.post(
		'/opera/video/collection/insert/' + videoId + '/' + playgoerId,
	)
}
export const insertAudioComment = (audioId: number, content: any) => {
	return http.post('/opera/audio/comment/insert/' + audioId + '/' + content, {
		header: {
			'content-type': 'application/x-www-form-urlencoded',
			satoken: localStorage.getItem('token'),
		},
	})
}
export const insertVideoComment = (videoId: number, content: any) => {
	return http.post('/opera/video/comment/insert/' + videoId + '/' + content, {
		header: {
			'content-type': 'application/x-www-form-urlencoded',
			satoken: localStorage.getItem('token'),
		},
	})
}
