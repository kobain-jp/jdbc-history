const pageInput = Vue.component('page-input', {
  props: ['mode', 'id', 'readOnly'],
  data: function () {
    return {
      form: {
        bookId: '',
        isbn: '',
        title: '',
        author: '',
        releaseDate: '',
      }
    }
  }, methods: {

    execute: function () {

      //　v-onで{{mode}}でバインドしたいがやり方がわからず。。
      if (this.mode === 'create') {
        this.create();
      } else if (this.mode === 'update') {
        this.update();
      } else if (this.mode === 'delete') {
        this.delete();
      } else {

      }

    },
    create: function () {
      axios.post('/api/book', this.form)
        .then(response => {
          router.push({ name: 'index' });
          this.showSuccessToast('create succeeded');
        })
        .catch(response => console.log(response));

    },
    update: function () {
      axios.put('/api/book/' + this.$route.params.bookId, this.form)
        .then(response => {
          router.push({ name: 'index' });
          this.showSuccessToast('update succeeded');
        })
        .catch(response => console.log(response));

    },
    delete: function () {
      axios.delete('/api/book/' + this.$route.params.bookId)
        .then(response => {
          router.push({ name: 'index' });
          this.showSuccessToast('delete succeeded');
        })
        .catch(response => console.log(response));

    },
    fetchById: function (id) {
      axios.get('/api/book/' + id)
        .then(response => this.form = response.data)
        .catch(response => console.log(response));
    },
    showSuccessToast: function (msg) {
      this.$toasted.success(msg, {
        position: "bottom-right",
        duration: 2000
      });
    }

  }, mounted: function () {

    if (this.mode === 'create') {
      return;
    }
    this.fetchById(this.$route.params.bookId);

  }, computed: {
    isButtonDisabled: function () {
      return (this.form.isbn.length === 0 || this.form.title.length === 0 || this.form.author.length === 0 || this.form.releaseDate.length === 0);
    }
  },
  template: '#page-input'
})

const pageIndex = Vue.component('page-index', {
  data: function () {
    return {
      books: []
    }
  }, methods: {

    fetch: function () {
      axios.get('/api/book')

        .then(response => this.books = response.data)
        .catch(response => console.log(response));

    },
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
  { name: 'index', path: '/index', component: pageIndex },
  { name: 'new', path: '/new', component: pageInput, props: { mode: 'create', readOnly: false } },
  { name: 'edit', path: '/edit/:bookId', component: pageInput, props: { mode: 'update', readOnly: false } },
  { name: 'delete', path: '/delete/:bookId', component: pageInput, props: { mode: 'delete', readOnly: true } }
]

const router = new VueRouter({
  routes: routes
})

new Vue({
  el: '#app',
  router: router
});
Vue.use(Toasted);

router.push({ name: 'index' }).catch(err => { })