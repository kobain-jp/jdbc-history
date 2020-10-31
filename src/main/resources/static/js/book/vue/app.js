const pageInput =Vue.component('page-input', {
  data: function () {
    return {
      form: {
        isbn: '',
        title: '',
        author: '',
        releaseDate: '',
      }
    }
  }, methods: {

    doCreateBook: function () {
      axios.post('/api/book', this.form)
        .then(response => router.push({ path: 'index' }) )
        .catch(response => console.log(response));
    }

  },
  template: '#page-input'
})

const pageIndex =Vue.component('page-index', {
  data: function () {
    return {
      books: []
    }
  }, methods: {

    fetch: function () {
      axios.get('/api/book')
        .then(response => this.books = response.data)
        .catch(response => console.log(response));

    }
    ,
    searchByTitle: function () {

      let title = this.$refs.searchValue.value;

      axios.get('/api/book-title', { params: { title: title } })
        .then(response => this.books = response.data)
        .catch(response => console.log(response));
    }

  },

  mounted: function () {
    this.fetch();
  },
  template: '#page-index'
});

const routes = [
  { path: '/index', component: pageIndex },
  { path: '/input', component: pageInput }
]

const router = new VueRouter({
	routes: routes
})

new Vue({
  el: '#app',
  router: router
});

router.push({ path: 'index' })