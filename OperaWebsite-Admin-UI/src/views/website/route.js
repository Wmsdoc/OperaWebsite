const Layout = () => import('@/layout/index.vue')

export default {
  name: 'Website',
  path: '/website',
  component: Layout,
  redirect: '/website',
  meta: {
    title: '网站管理',
    icon: 'ic:baseline-menu',
    // role: ['admin'],
    // requireAuth: true,
    order: 5,
  },
  children: [
    {
      name: 'notice',
      path: 'notice',
      component: () => import('./notice/index.vue'),
      meta: {
        title: '公告管理',
        icon: 'ic:baseline-menu',
        keepAlive: true,
        // role: ['admin'],
        // requireAuth: true,
      },
    },
    {
      name: 'advert',
      path: 'advert',
      component: () => import('./advert/index.vue'),
      meta: {
        title: '广告管理',
        icon: 'ic:baseline-menu',
        keepAlive: true,
        // role: ['admin'],
        // requireAuth: true,
      },
    },
  ],
}
