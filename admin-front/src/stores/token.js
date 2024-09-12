import { defineStore } from 'pinia'
import { ref } from 'vue'
export  const token = defineStore('store',  () => {
    const token = ref(null)
    return { token }
  }, {
    persist: {
      storage: localStorage,
      pick: ['token'],
    },
  })