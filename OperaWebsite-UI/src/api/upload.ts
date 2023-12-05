export const fileUpload = (formData: any) => {
	return http.post('/file/upload', formData, {
		headers: {
			'Content-Type': 'multipart/form-data',
			satoken: localStorage.getItem('token'),
		},
	})
}
