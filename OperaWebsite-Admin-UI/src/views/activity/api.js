import { request } from '@/utils'

export default {
  getPosts: (params = {}) => request.get('/activity/getByPageAndParams', { params }),
  getPostById: (activityId) =>
    request.get('/activity/getById', {
      params: {
        activityId: activityId,
      },
    }),
  getActivityById: (activityId) =>
    request.get('/activity/getActivityById', {
      params: {
        activityId: activityId,
      },
    }),
  addPost: (data) =>
    request.post('/activity/insert', data, {
      headers: {
        'Content-Type': 'application/json',
      },
    }),
  updatePost: (data) =>
    request.put(`/activity/update`, data, {
      headers: {
        'Content-Type': 'application/json',
      },
    }),
  deletePost: (activityId) => request.delete(`/activity/delete/${activityId}`),
}
