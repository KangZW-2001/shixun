import Vue from 'vue'
import Router from 'vue-router'
import Input from '@/components/Input'
import MainPage from '@/pages/MainPage'
import SubPage from '@/pages/SubPage'
import IndexPage from '@/pages/IndexPage'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/input',
      name: 'Input',
      component: Input
    },
    {
      path: '/sub',
      name: 'SubPage',
      component: SubPage
    },
    {
      path: '/main',
      name: 'MainPage',
      component: MainPage
    },
    {
      path: '/indexpage',
      name: 'IndexPage',
      component: IndexPage
    },

  ]
})
