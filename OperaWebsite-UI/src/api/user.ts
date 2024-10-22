/**
 * 登录
 */
export const login = (data: any) => {
	return http.post(`/system/login`, data, {
		// headers: {
		// 	'content-type': 'application/x-www-form-urlencoded',
		// },
		headers: {
			'Content-Type': 'application/json',
		},
	})
}
/**
 * 注册
 */
export const register = (data: any) => {
	return http.post(`/system/register`, data, {
		// headers: {
		// 	'content-type': 'application/x-www-form-urlencoded',
		// },
		headers: {
			'Content-Type': 'application/json',
		},
	})
}
//判断用户是否登录
export const islogin = () => {
	return http.post(`/system/isLogin`)
}
//退出登录
export const logout = () => {
	return http.post(`/system/logout`)
}
//根据ID获取用户信息
export const getPlaygoer = (playgoerId: any) => {
	return http.post(`/activity/playgoer/getById/` + playgoerId)
}
//修改用户信息
export const updatePlaygoer = (formData: any) => {
	return http.put('/activity/playgoer/updateById', formData, {
		headers: {
			'Content-Type': 'application/json',
			satoken: localStorage.getItem('token'),
		},
	})
}
//根据token获取用户收藏的戏曲音频
export const getPlaygoerAudioCollect = (pageNum: any, pageSize: any) => {
	return http.post(
		`/opera/audio/collection/getCollectByPlaygoerId/` +
			pageNum +
			'/' +
			pageSize,
		{
			headers: {
				satoken: localStorage.getItem('token'),
			},
		},
	)
}
//根据token获取用户收藏的戏曲视频
export const getPlaygoerVideoCollect = (pageNum: any, pageSize: any) => {
	return http.post(
		`/opera/video/collection/getCollectByPlaygoerId/` +
			pageNum +
			'/' +
			pageSize,
		{
			headers: {
				satoken: localStorage.getItem('token'),
			},
		},
	)
}
//根据ID获取用户的戏曲音频评论
export const getPlaygoerAudioComment = (
	playgoerId: any,
	pageNum: any,
	pageSize: any,
) => {
	return http.post(
		`/opera/audio/comment/getCommentByPlaygoerId/` +
			playgoerId +
			'/' +
			pageNum +
			'/' +
			pageSize,
	)
}
//根据ID获取用户的戏曲视频评论
export const getPlaygoerVideoComment = (
	playgoerId: any,
	pageNum: any,
	pageSize: any,
) => {
	return http.post(
		`/opera/video/comment/getCommentByPlaygoerId/` +
			playgoerId +
			'/' +
			pageNum +
			'/' +
			pageSize,
	)
}
//根据token获取用户参加的活动
export const getPlaygoerActivity = (pageNum: any, pageSize: any) => {
	return http.post(
		`/activity/playgoerActivity/getActivityByPlaygoerId/` +
			pageNum +
			'/' +
			pageSize,
		{
			headers: {
				satoken: localStorage.getItem('token'),
			},
		},
	)
}

//修改用户音频评论
export const updateAudioCommentInfo = (formData: any) => {
	return http.put('/opera/audio/comment/update', formData, {
		headers: {
			satoken: localStorage.getItem('token'),
		},
	})
}
//修改用户视频评论
export const updateVideoCommentInfo = (formData: any) => {
	return http.put('/opera/video/comment/update', formData, {
		headers: {
			satoken: localStorage.getItem('token'),
		},
	})
}
//删除用户音频评论
export const deleteAudioComment = (commentId: any) => {
	return http.delete(`/opera/audio/comment/delete/` + commentId, {
		headers: {
			satoken: localStorage.getItem('token'),
		},
	})
}
//删除用户视频评论
export const deleteVideoComment = (commentId: any) => {
	return http.delete(`/opera/video/comment/delete/` + commentId, {
		headers: {
			satoken: localStorage.getItem('token'),
		},
	})
}
//获取用户的戏曲音频上传
export const getPlaygoerAudioUpload = (pageNum: any, pageSize: any) => {
	return http.get(`/opera/audio/getAudioByCreated`, {
		params: {
			pageNum: pageNum,
			pageSize: pageSize,
		},
		headers: {
			satoken: localStorage.getItem('token'),
		},
	})
}
//获取用户的戏曲视频上传
export const getPlaygoerVideoUpload = (pageNum: any, pageSize: any) => {
	return http.get(`/opera/video/getVideoByCreated`, {
		params: {
			pageNum: pageNum,
			pageSize: pageSize,
		},
		headers: {
			satoken: localStorage.getItem('token'),
		},
	})
}
//修改用户音频上传
export const updateAudioUploadInfo = (formData: any) => {
	return http.put('/opera/audio/update', formData, {
		headers: {
			satoken: localStorage.getItem('token'),
		},
	})
}
//修改用户视频上传
export const updateVideoUploadInfo = (formData: any) => {
	return http.put('/opera/video/update', formData, {
		headers: {
			satoken: localStorage.getItem('token'),
		},
	})
}
//删除用户音频上传
export const deleteAudioUpload = (audioId: any) => {
	return http.delete(`/opera/audio/delete/` + audioId, {
		headers: {
			satoken: localStorage.getItem('token'),
		},
	})
}
//删除用户视频上传
export const deleteVideoUpload = (videoId: any) => {
	return http.delete(`/opera/video/delete/` + videoId, {
		headers: {
			satoken: localStorage.getItem('token'),
		},
	})
}
