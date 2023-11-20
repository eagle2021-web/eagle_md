main = main.cpp

hello: $(main)
	g++ $(main) -o hello
