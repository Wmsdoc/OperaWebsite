import { defineStore } from 'pinia'
import { resetRouter } from '@/router'
import { useTagsStore, usePermissionStore } from '@/store'
import { lStorage, removeToken, toLogin } from '@/utils'
import api from '@/api'

export const useUserStore = defineStore('user', {
  state() {
    return {
      userInfo: {},
    }
  },
  getters: {
    userId() {
      return this.userInfo?.playgoerId
    },
    name() {
      return this.userInfo?.playgoerName
    },
    avatar() {
      return this.userInfo?.playgoerAvatar
    },
    role() {
      return this.userInfo?.role || []
    },
  },
  actions: {
    async getUserInfo() {
      try {
        // const res = await api.getUser()
        const id = lStorage.get('playgoerId')
        const res = await api.getUser(id)
        console.log('getUserInfo', res)
        const { playgoerId, playgoerName, playgoerAvatar, role } = res.data
        //设置权限为null
        this.userInfo = { playgoerId, playgoerName, playgoerAvatar, role }
        return Promise.resolve(res.data)
      } catch (error) {
        return Promise.reject(error)
      }
    },
    async logout() {
      const { resetTags } = useTagsStore()
      const { resetPermission } = usePermissionStore()
      removeToken()
      resetTags()
      resetPermission()
      resetRouter()
      this.$reset()
      toLogin()
    },
    setUserInfo(userInfo = {}) {
      this.userInfo = { ...this.userInfo, ...userInfo }
    },
  },
})
