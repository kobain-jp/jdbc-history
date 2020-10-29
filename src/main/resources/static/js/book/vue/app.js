new Vue({
  el: '#app',
  data: {
    books: []
  }, methods: {

    searchByTitle: function () {

      let title = this.$refs.searchValue.value;

      axios.get('/api/book-title', { params: { title: title } })
        .then(response => this.books = response.data)
        .catch(response => console.log(response));

    }

  },

  mounted: function () {

    axios.get('/api/book')
      .then(response => this.books = response.data)
      .catch(response => console.log(response));

  }
})