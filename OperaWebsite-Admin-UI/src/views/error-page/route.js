const Layout = () => import('@/layout/index.vue')

export default {
  name: 'ErrorPage',
  path: '/error-page',
  component: Layout,
  redirect: '/error-page/404',
  meta: {
    title: '错误页',
    icon: 'mdi:alert-circle-outline',
    //设置角色，只有角色为admin的用户才能看到这个菜单
    role: ['admin'],
    requireAuth: true,
    order: 99,
  },
  children: [
    {
      name: 'ERROR-404',
      path: '404',
      component: () => import('./404.vue'),
      meta: {
        title: '404',
        icon: 'tabler:error-404',
      },
    },
  ],
}
