const Layout = () => import('@/layout/index.vue')

export default {
  name: 'Opera',
  path: '/opera',
  component: Layout,
  redirect: '/opera',
  meta: {
    title: '戏曲管理',
    icon: 'ic:baseline-menu',
    // role: ['admin'],
    // requireAuth: true,
    order: 5,
  },
  children: [
    {
      name: 'type',
      path: 'type',
      component: () => import('./type/index.vue'),
      meta: {
        title: '类型管理',
        icon: 'ic:baseline-menu',
        keepAlive: true,
        // role: ['admin'],
        // requireAuth: true,
      },
    },
    {
      name: 'audio',
      path: 'audio',
      component: () => import('./audio/index.vue'),
      meta: {
        title: '音频管理',
        icon: 'ic:baseline-menu',
        keepAlive: true,
        // role: ['admin'],
        // requireAuth: true,
      },
      children: [
        {
          name: 'audio-details',
          path: 'details',
          component: () => import('./audio/details/index.vue'),
          meta: {
            title: '音频列表',
            icon: 'ic:baseline-menu',
          },
        },
        {
          name: 'audio-comment',
          path: 'comment',
          component: () => import('./audio/comment/index.vue'),
          meta: {
            title: '音频评论',
            icon: 'ic:baseline-menu',
          },
        },
      ],
    },
    {
      name: 'video',
      path: 'video',
      component: () => import('./video/index.vue'),
      meta: {
        title: '视频管理',
        icon: 'ic:baseline-menu',
        keepAlive: true,
        // role: ['admin'],
        // requireAuth: true,
      },
      children: [
        {
          name: 'video-details',
          path: 'details',
          component: () => import('./video/details/index.vue'),
          meta: {
            title: '视频列表',
            icon: 'ic:baseline-menu',
          },
        },
        {
          name: 'video-comment',
          path: 'comment',
          component: () => import('./video/comment/index.vue'),
          meta: {
            title: '视频评论',
            icon: 'ic:baseline-menu',
          },
        },
      ],
    },
  ],
}
