import { request } from '@/utils'

export default {
  getUser: (playgoerId) => request.post('/activity/playgoer/getById/' + playgoerId),
  refreshToken: () => request.post('/auth/refreshToken', null, { noNeedTip: true }),
}
