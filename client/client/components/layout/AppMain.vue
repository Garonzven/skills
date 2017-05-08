<template>
  <section class="app-main">
    <div class="container is-fluid is-marginless app-content">
      <levelbar></levelbar>
      <transition
        mode="out-in"
        enter-active-class="fadeIn"
        leave-active-class="fadeOut"
        appear>
        <router-view class="animated"></router-view>
      </transition>
    </div>
  </section>
</template>

<script>
import Levelbar from './Levelbar'

export default {
  components: {
    Levelbar
  }
}
</script>

<style lang="scss">
@import '~bulma/sass/utilities/variables';
@import '~bulma/sass/utilities/mixins';

.app-main {
  padding-top: 50px;
  margin-left: 300px;

  transform: translate3d(0, 0, 0);

  @include mobile() {
    margin-left: 0;
  }

}

@media screen and (min-width: 1000px) {
  .container.is-fluid {
    margin: 0 20px;
    max-width: 700px;
  }
}

@media screen and (min-width: 1100px) {
  .container.is-fluid {
    margin: 0 20px;
    max-width: 900px;
  }
}

.app-content {
  padding: 20px;
}
</style>
