const Layout = () => import('@/layout/index.vue')

export default {
  name: 'User',
  path: '/',
  component: Layout,
  redirect: '/user',
  meta: {
    title: '用户管理',
    icon: 'material-symbols:fitbit-hourly-activity-rounded',
    // role: ['admin'],
    // requireAuth: true,
    order: 4,
  },
  children: [
    {
      name: 'User',
      path: 'user',
      component: () => import('./index.vue'),
      meta: {
        title: '用户管理',
        icon: 'material-symbols:fitbit-hourly-activity-rounded',
        order: 4,
      },
    },
  ],
}
