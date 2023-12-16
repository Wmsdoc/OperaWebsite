import { request } from '@/utils'

export default {
  getPosts: (params = {}) => request.get('/opera/audio/comment/getByPageAndParams', { params }),
  getOperaType: () => request.get('/opera/type/getAllOperaType'),
  updatePost: (data) => request.put(`/opera/audio/comment/update`, data),
  deletePost: (commentId) => request.delete(`/opera/audio/comment/delete/${commentId}`),
}
