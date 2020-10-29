(function () {

    'use strict';

    function Model() {
        let self = this;
        let books = [];

        // observer pattern
        this.observers = [];

        this.registerObserver = function (observer) {
            self.observers.push(observer);
        }

        this.notifyAll = function () {
            self.observers.forEach(function (observer) {
                observer.update(self);
            })
        }

        Object.defineProperty(this, "books", {
            get: function () { return books; },
            set: function (value) {
                books = value;
                this.notifyAll();
            }
        });

        // fetch
        this.fetch = function () {
            axios.get('/api/book')
                .then(response => self.books = response.data)
                .catch(response => console.log(response))
        }

        this.fetchByTitle = function (title) {
            axios.get('/api/book-title', { params: { title: title } })
                .then(response => this.books = response.data)
                .catch(response => console.log(response));
        }
    }

    function View() {

        this.elmInpSearch = document.getElementById('inp-search');
        this.formSearch = document.getElementById('form-search');
        this.elemTbodyBookList = document.getElementById('tbody-booklist');

        this.template = "<td>{{book.isbn}}</td><td>{{book.title}}</td><td>{{book.author}}</td><td>{{book.releaseDate}}</td>"

        this.renderTblBody = function (books) {

            this.elemTbodyBookList.innerHTML = '';

            books.map(book => {
                const elemTr = document.createElement("tr");
                const compiled = this.template.replace('{{book.isbn}}', book.isbn).replace('{{book.title}}', book.title).replace('{{book.author}}', book.author).replace('{{book.releaseDate}}', book.releaseDate);
                elemTr.innerHTML = compiled;
                this.elemTbodyBookList.appendChild(elemTr);
            });
        }

        this.update = function (model) {
            this.renderTblBody(model.books);
        }

    }

    function Controller(model, view) {
        var self = this;
        this.model = model;
        this.view = view;
        model.registerObserver(view);

        this.bindEvents = function () {
            view.formSearch.addEventListener("submit", self.submitFormSearch);
        }

        // call action by hash as Router
        this.dispatch = function (hash) {

        }

        // action
        this.index = function () {
            this.model.fetch();
        }

        // hashchange
        this.submitFormSearch = function (event) {
            event.preventDefault();
            const title = self.view.elmInpSearch.value;
            self.model.fetchByTitle(title);
            return false;
        }

        this.bindEvents();
    }

    function App() {
        this.model = new Model();
        this.view = new View();
        this.controller = new Controller(this.model, this.view);
    }

    const app = new App();

    // as Mounted
    window.addEventListener('load', (event) => {
        app.controller.index();
    });

    // as Router
    window.addEventListener('hashchange', (event) => {
        app.controller.dispatch(document.location.hash);
    });

}());