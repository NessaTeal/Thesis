import System.Environment
import Control.Monad
import Data.Char

main::IO ()
main = do
  args <- getArgs

  when (length args /= 1) (error "Argument is not supplied supplied")

  let nString = head args

  unless (all isDigit nString) (error "Argument should be integer")

  print $ fib $ read nString

fib :: Int -> Int
fib 1 = 1
fib 2 = 1
fib n = fib (n - 1) + fib (n - 2)
