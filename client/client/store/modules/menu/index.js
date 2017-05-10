import * as types from '../../mutation-types'
import lazyLoading from './lazyLoading'

// show: meta.label -> name
// name: component name
// meta.label: display label

const state = {
  items: [
    {
      name: 'Users',
      path: '/dashboard',
      meta: {
        icon: 'fa-tachometer',
        link: 'dashboard/index.vue'
      },
      component: lazyLoading('dashboard', true)
    },
    {
      name: 'Skills',
      path: '/user',
      meta: {
        icon: 'fa-tachometer',
        link: 'users/index.vue'
      },
      component: lazyLoading('users', true)
    },
    {
      name: 'Reports',
      path: '/reports',
      meta: {
        icon: 'fa-tachometer',
        link: 'skills/index.vue'
      },
      component: lazyLoading('skills', true)
    },
    {
      name: 'My Skills',
      path: '/userProfile',
      meta: {
        icon: 'fa-tachometer',
        link: 'userProfile/index.vue'
      },
      component: lazyLoading('userProfile', true)
    }
  ]
}

const mutations = {
  [types.EXPAND_MENU] (state, menuItem) {
    if (menuItem.index > -1) {
      if (state.items[menuItem.index] && state.items[menuItem.index].meta) {
        state.items[menuItem.index].meta.expanded = menuItem.expanded
      }
    } else if (menuItem.item && 'expanded' in menuItem.item.meta) {
      menuItem.item.meta.expanded = menuItem.expanded
    }
  }
}

export default {
  state,
  mutations
}
