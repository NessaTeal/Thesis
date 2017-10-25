import System.Environment

main :: IO ()
main = do
  args <- getArgs
  print (summ $ read (head args)::Integer)

summ :: Integer -> Integer
summ n = sum [1..n]
