import { request } from '@/utils'

export default {
  getPosts: (params = {}) => request.get('/activity/playgoer/getByPageAndParams', { params }),
  getPostById: (playgoerId) =>
    request.get('/activity/playgoer/getById', {
      params: {
        playgoerId: playgoerId,
      },
    }),
  // addPost: (data) =>
  //   request.post('/playgoer/insert', data, {
  //     headers: {
  //       'Content-Type': 'application/json',
  //     },
  //   }),
  updatePost: (data) =>
    request.put(`/activity/playgoer/updateById`, data, {
      headers: {
        'Content-Type': 'application/json',
      },
    }),
  updateAccountStatue: (playgoerId) => request.put(`/system/updateAccountStatue/${playgoerId}`),
}
