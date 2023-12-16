import { request } from '@/utils'

export default {
  getPosts: (params = {}) => request.get('/opera/audio/getByPageAndParams', { params }),
  getPostById: (audioId) =>
    request.get('/opera/audio/getById', {
      params: {
        audioId: audioId,
      },
    }),
  getAudioById: (audioId) =>
    request.get('/opera/audio/getAudioById', {
      params: {
        audioId: audioId,
      },
    }),
  getOperaType: () => request.get('/opera/type/getAllOperaType'),
  updatePost: (data) => request.put(`/opera/audio/update`, data),
  deletePost: (audioId) => request.delete(`/opera/audio/delete/${audioId}`),
}
