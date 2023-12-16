import { request } from '@/utils'

export default {
  login: (data) =>
    request.post('/system/login', data, {
      headers: {
        'Content-Type': 'application/json',
      },
      noNeedToken: true,
    }),
}
