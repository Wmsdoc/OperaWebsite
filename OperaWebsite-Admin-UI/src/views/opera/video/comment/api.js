import { request } from '@/utils'

export default {
  getPosts: (params = {}) => request.get('/opera/video/comment/getByPageAndParams', { params }),
  getOperaType: () => request.get('/opera/type/getAllOperaType'),
  updatePost: (data) => request.put(`/opera/video/comment/update`, data),
  deletePost: (commentId) => request.delete(`/opera/video/comment/delete/${commentId}`),
}
