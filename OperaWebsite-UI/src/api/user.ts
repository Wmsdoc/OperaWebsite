/**
 * 登录
 */
export const login = (data: any) => {
	return http.post(`/system/doLogin`, data, {
		// headers: {
		// 	'content-type': 'application/x-www-form-urlencoded',
		// },
		headers: {
			'Content-Type': 'application/json',
		},
	})
}
export const islogin = () => {
	return http.post(`/system/isLogin`)
}

export const logout = () => {
	return http.post(`/system/logout`)
}

export const getPlaygoer = (playgoerId: any) => {
	return http.post(`/activity/playgoer/getById/` + playgoerId)
}

export const updatePlaygoer = (formData: any) => {
	return http.put('/activity/playgoer/updateById', formData, {
		headers: {
			'Content-Type': 'application/json',
			satoken: localStorage.getItem('token'),
		},
	})
}
// /**
//  * 获取localStorage中的用户信息
//  */
// export const getUserStorage = () => {
// 	const user = localStorage.getItem('user')
// 	console.log(user)
// }
