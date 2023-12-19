import { request } from '@/utils'

export default {
  getPosts: (params = {}) => request.get('/system/getAdvert', { params }),
  addPost: (data) =>
    request.post('/system/addAdvert', data, {
      headers: {
        'Content-Type': 'application/json',
      },
    }),
  deletePost: (advertId) => request.delete(`/system/deleteAdvert/${advertId}`),
  upload: (data) =>
    request.post('/file/upload', data, {
      headers: {
        'Content-Type': 'multipart/form-data',
        satoken: localStorage.getItem('token'),
      },
    }),
}
