angular.module('todoApp', [])
  .controller('TodoListController', function($scope, $http) {
	  
	  /*
	   * 
	   * TESTE POST
	   */
	var dataObj = {
			nome: 'Joao Augusto Locatelli',
			pos : 1,
			prioridade : 2,
			quantidadeMinistrada : 3
	};	
	var res = $http.post('/CDD/rest/fila/testePost', dataObj);
	res.success(function(data, status, headers, config) {
		$scope.message = data;
		alert(data);
	});
	res.error(function(data, status, headers, config) {
		alert( "failure message: " + JSON.stringify({data: data}));
	});
	
    var todoList = this;
    todoList.todos = [
      {text:'learn angular', done:true},
      {text:'build an angular app', done:false}];
 
    todoList.addTodo = function() {
      todoList.todos.push({text:todoList.todoText, done:false});
      todoList.todoText = '';
    };
 
    todoList.remaining = function() {
      var count = 0;
      angular.forEach(todoList.todos, function(todo) {
        count += todo.done ? 0 : 1;
      });
      return count;
    };
 
    todoList.archive = function() {
      var oldTodos = todoList.todos;
      todoList.todos = [];
      angular.forEach(oldTodos, function(todo) {
        if (!todo.done) todoList.todos.push(todo);
      });
    };
  });