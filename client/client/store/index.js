import Vue from 'vue'
import Vuex from 'vuex'
import pkg from 'package'
import * as actions from './actions'
import * as getters from './getters'

import app from './modules/app'
import menu from './modules/menu'

Vue.use(Vuex)

const store = new Vuex.Store({
  strict: true, // process.env.NODE_ENV !== 'production',
  actions,
  getters,
  modules: {
    app,
    menu
  },
  state: {
    pkg
  },
  mutations: {
    changeUserName (state, currentName) {
      // mutate state
      state.loggedUser.userName = currentName
    },
    changeEmail (state, currentEmail) {
      state.loggedUser.email = currentEmail
    }
  }
})

export default store
