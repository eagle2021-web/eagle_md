import unittest   # The test framework
# import pdb; 
class Test_A(unittest.TestCase):
    def test_1(self):
        # pdb.set_trace()
        print(21)
        print(21)
        # pdb.set_trace()
        self.assertEqual(4, 4)

    # This test is designed to fail for demonstration purposes.
    def test_2(self):
        print(21)
        self.assertEqual(4, 4)

if __name__ == '__main__':
    # unittest.main()
    unittest.main(defaultTest='Test_A.test_1')