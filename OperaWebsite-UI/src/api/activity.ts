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
