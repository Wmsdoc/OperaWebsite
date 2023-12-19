import { request } from '@/utils'

export default {
  getPosts: (params = {}) => request.get('/system/getNotice', { params }),
  addPost: (data) =>
    request.post('/system/addNotice', data, {
      headers: {
        'Content-Type': 'application/json',
      },
    }),
  deletePost: (typeId) => request.delete(`/system/deleteNotice/${typeId}`),
}
