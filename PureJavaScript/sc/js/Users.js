Vue.use(VeeValidate);
Vue.component('v-select', VueSelect.VueSelect);
/* var users = []

    $.ajax({
      url: 'http://localhost:8080/WebSkillsGW7/webresources/entis.people/',
      type: 'GET',
      success: function (data) {
        users = data
        console.log(users)
      },
      dataType: 'json'
    }) */
var User = new Vue({
  el: '#app',
  data: () => ({
    selected: [],
    users: [],
    skillList: skillList
  }),

  // Fetches posts when the component is created.
  created() {
    axios.get(`http://localhost:8080/WebSkillsGW7/webresources/entis.people/`)
      .then(response => {
        // JSON responses are automatically parsed.
        this.users = response.data
      })
    /* .catch(e => {
      this.errors.push(e)
    }) */

    // async / await version (created() becomes async created())
    //
    // try {
    //   const response = await axios.get(`http://jsonplaceholder.typicode.com/posts`)
    //   this.posts = response.data
    // } catch (e) {
    //   this.errors.push(e)
    // }
  },
  methods: {
    search() {
      console.log('search for:', this.selected)
      if (this.selected.length === 0) {
        axios.get(`http://localhost:8080/WebSkillsGW7/webresources/entis.people/`)
          .then(response => {
            // JSON responses are automatically parsed.
            this.users = response.data
          })
      } else {
        console.log('search for:', this.users)
        this.users = []
      }
    }
  }
})
