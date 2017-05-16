<template>
  <aside class="menu app-sidebar animated" :class="{ slideInLeft: show, slideOutLeft: !show }">
    <div class="userData">
      <div class="profile-userpic">
        <img src="https://pbs.twimg.com/profile_images/784477860497063936/kH8KiYXU.jpg" 
             class="img-responsive" alt="">
      </div>
      <!-- END SIDEBAR USERPIC -->
      <!-- SIDEBAR USER TITLE -->
      <div class="profile-usertitle">
        <div class="profile-usertitle-name">
          {{loggedUser.userName}}
        </div>
        <div class="profile-usertitle-job">
          {{loggedUser.email}}
        </div>
      </div>
      <!-- END SIDEBAR USER TITLE -->
    </div>
    <ul class="menu-list">
      <li v-for="(item, index) in menu">
        <router-link :to="item.path" :exact="true" :aria-expanded="isExpanded(item) ? 'true' : 'false'" v-if="item.path" @click.native="toggle(index, item)">
          <span class="icon is-medium"><i :class="['fa', item.meta.icon]"></i></span>
          {{ item.name || item.meta.label }}
          <span class="icon is-small is-angle" v-if="item.children && item.children.length">
            <i class="fa fa-angle-down"></i>
          </span>
        </router-link>
        <a :aria-expanded="isExpanded(item)" v-else @click="toggle(index, item)">
          <span class="icon is-medium" ><i :class="['fa', item.meta.icon]"></i></span>
          {{ item.meta.label || item.name }}
          <span class="icon is-small is-angle" v-if="item.children && item.children.length">
            <i class="fa fa-angle-down"></i>
          </span>
        </a>

        <expanding v-if="item.children && item.children.length">
          <ul v-show="isExpanded(item)">
            <li v-for="subItem in item.children" v-if="subItem.path">
              <router-link :to="generatePath(item, subItem)">
                {{ subItem.meta && subItem.meta.label || subItem.name }}
              </router-link>
            </li>
          </ul>
        </expanding>
      </li>
    </ul>
  </aside>
</template>

<script>
  import Expanding from 'vue-bulma-expanding'
  import {
    mapGetters,
    mapActions
  } from 'vuex'

  export default {
    components: {
      Expanding
    },

    props: {
      show: Boolean
    },

    data () {
      return {
        loggedUser: {
          userName: 'Jhon Doe',
          email: 'Somebody@garonz.com'
        },
        isReady: false
      }
    },
    mounted () {
      let route = this.$route
      this.$store.state.loggedUser = this.loggedUser
      if (route.name) {
        this.isReady = true
        this.shouldExpandMatchItem(route)
      }
    },

    computed: mapGetters({
      menu: 'menuitems'
    }),

    methods: {
      ...mapActions([
        'expandMenu'
      ]),

      isExpanded (item) {
        return item.meta.expanded
      },

      toggle (index, item) {
        this.expandMenu({
          index: index,
          expanded: !item.meta.expanded
        })
      },

      shouldExpandMatchItem (route) {
        let matched = route.matched
        let lastMatched = matched[matched.length - 1]
        let parent = lastMatched.parent || lastMatched
        const isParent = parent === lastMatched

        if (isParent) {
          const p = this.findParentFromMenu(route)
          if (p) {
            parent = p
          }
        }

        if ('expanded' in parent.meta && !isParent) {
          this.expandMenu({
            item: parent,
            expanded: true
          })
        }
      },

      generatePath (item, subItem) {
        return `${item.component ? item.path + '/' : ''}${subItem.path}`
      },

      findParentFromMenu (route) {
        const menu = this.menu
        for (let i = 0, l = menu.length; i < l; i++) {
          const item = menu[i]
          const k = item.children && item.children.length
          if (k) {
            for (let j = 0; j < k; j++) {
              if (item.children[j].name === route.name) {
                return item
              }
            }
          }
        }
      }
    },

    watch: {
      $route (route) {
        this.isReady = true
        this.shouldExpandMatchItem(route)
      }
    }

  }

</script>

<style>
  .app-sidebar {
    position: fixed;
    top: 50px;
    left: 0;
    bottom: 0;
    padding: 20px 0 50px;
    width: 300px;
    min-width: 45px;
    max-height: 100vh;
    height: calc(100% - 50px);
    z-index: 1023;
    background: #323132;
    box-shadow: 0 2px 3px rgba(17, 17, 17, 0.1), 0 0 0 1px rgba(17, 17, 17, 0.1);
    overflow-y: auto;
    overflow-x: hidden;
    color: #d1d2d4;
    text-align: center;
  }
  
  .profile-userpic img {
    float: none;
    margin: 0 auto;
    width: 50%;
    height: 50%;
    -webkit-border-radius: 50% !important;
    -moz-border-radius: 50% !important;
    border-radius: 50% !important;
  }
  
  .userData {
    padding: 30px;
    margin: 0 13px;
    border-bottom: 0.5px solid #d1d2d4;
  }
  
  @include mobile() {
    transform: translate3d(-180px, 0, 0);
  }
  
  .app-sidebar .icon {
    vertical-align: middle;
    /*height: 40px;
  position: absolute;
  align-content: center;
  right: 10px;
  left: 100px;*/
  }
  
  .app-sidebar .icon.is-angle {
    position: absolute;
    left: 10px;
    transition: transform .377s ease;
  }
  
  .app-sidebar .menu-label {
    padding-left: 5px;
  }
  
  .app-sidebar .menu-list li a {
    padding: 13px;
    margin: 0 13px;
    border-bottom: 0.5px solid #d1d2d4;
  }
  
  .app-sidebar .menu-list li a[aria-expanded="true"] .is-angle {
    transform: rotate(180deg);
  }
  
  .app-sidebar .menu-list li a+ul {
    margin: 0 10px 0 15px;
  }
  
  .menu-list a {
    color: #e6e6e6;
  }
  
  .menu-list a:hover {
    color: #f36523;
  }
  
  .menu-list a.is-active {
    background-color: #f36523;
  }
  
  .menu-list a.is-active:hover {
    color: #fff;
  }
  
  .profile-usertitle {
    text-align: center;
    margin-top: 20px;
  }
  
  .profile-usertitle-name {
    color: #e6e6e6;
    font-size: 16px;
    font-weight: 600;
    margin-bottom: 7px;
  }
  
  .profile-usertitle-job {
    text-transform: uppercase;
    color: #e6e6e6;
    font-size: 12px;
    font-weight: 600;
    margin-bottom: 15px;
  }

</style>
