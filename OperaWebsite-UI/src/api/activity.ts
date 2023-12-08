export function getActivities(queryParams: any) {
	return http.get('/activity/getByPageAndParams', {
		params: {
			pageSize: queryParams.pageSize,
			pageNum: queryParams.pageNum,
		},
	})
}
export function getActivityDetails(id: any) {
	return http.get('/activity/getById', {
		params: {
			activityId: id,
		},
	})
}
export function insertPlaygoerActivity(formData: any) {
	return http.post('/activity/playgoerActivity/insert', formData, {
		headers: {
			satoken: localStorage.getItem('token'),
		},
	})
}
//用户取消收藏
export const deletePlaygoerActivity = (activityId: any, playgoerId: any) => {
	return http.delete(`/activity/playgoerActivity/delete`, {
		params: {
			activityId,
			playgoerId,
		},
	})
}
