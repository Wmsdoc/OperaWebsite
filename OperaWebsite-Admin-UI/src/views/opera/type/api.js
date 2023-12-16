import { request } from '@/utils'

export default {
  getPosts: (params = {}) => request.get('/opera/type/getByPageAndParams', { params }),
  updatePost: (data) => request.put(`/opera/type/update`, data),
  deletePost: (typeId) => request.delete(`/opera/type/delete/${typeId}`),
}
