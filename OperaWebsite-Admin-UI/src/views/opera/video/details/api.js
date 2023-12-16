import { request } from '@/utils'

export default {
  getPosts: (params = {}) => request.get('/opera/video/getByPageAndParams', { params }),
  getPostById: (videoId) =>
    request.get('/opera/video/getById', {
      params: {
        videoId: videoId,
      },
    }),
  getVideoById: (videoId) =>
    request.get('/opera/video/getById', {
      params: {
        videoId: videoId,
      },
    }),
  getOperaType: () => request.get('/opera/type/getAllOperaType'),
  updatePost: (data) => request.put(`/opera/video/update`, data),
  deletePost: (videoId) => request.delete(`/opera/video/delete/${videoId}`),
}
