import System.Environment
import Control.Monad
import Data.Char

main :: IO ()
main = do
  args <- getArgs

  when (length args < 2) (error "Not enough arguments")

  let nString = head args
  let useBig = args !! 1

  unless (all isDigit nString) (error "First argument should be integer")
  unless (useBig == "False" || useBig == "True") (error "Second argument should be \"True\" or \"False\"")

  if read useBig::Bool
    then print (summ $ read nString::Integer)
    else print (summ $ read nString::Int)

summ :: (Integral a) => a -> a
summ n = sum [1..n]
