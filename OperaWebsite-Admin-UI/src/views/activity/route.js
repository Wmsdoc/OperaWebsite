const Layout = () => import('@/layout/index.vue')

export default {
  name: 'Activity',
  path: '/',
  component: Layout,
  redirect: '/activity',
  meta: {
    title: '活动管理',
    icon: 'material-symbols:fitbit-hourly-activity-rounded',
    // role: ['admin'],
    // requireAuth: true,
    order: 6,
  },
  children: [
    {
      name: 'Activity',
      path: 'activity',
      component: () => import('./index.vue'),
      meta: {
        title: '活动管理',
        icon: 'material-symbols:fitbit-hourly-activity-rounded',
        order: 6,
      },
    },
  ],
}
