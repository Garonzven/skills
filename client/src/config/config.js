export default function (Vue) {
  Vue.api = {
    Url: 'http://13.92.199.15:38967/WebSkillsGW7/webresources'
  }

  Object.defineProperties(Vue.prototype, {
    $apiURL: {
      get() {
        return Vue.api
      }
    }
  })
}
