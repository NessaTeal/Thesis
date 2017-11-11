import qualified Data.Map.Lazy as Map
import qualified Data.Set as Set

main :: IO ()
main = print $ findShortestDistances createGraph (Node 0)

type Id = Int
type Cost = Int
type Distance = Int

newtype Node = Node Id deriving (Show, Eq, Ord)
type Origin = Node
type Destination = Node

createGraph::Map.Map Origin (Map.Map Destination Cost)
createGraph = Map.fromList [(Node 0, Map.fromList [(Node 1, 3), (Node 2, 4)]),
                (Node 1, Map.fromList [(Node 0, 3), (Node 3, 5), (Node 4, 6)]),
                (Node 2, Map.fromList [(Node 0, 4), (Node 3, 2), (Node 6, 7)]),
                (Node 3, Map.fromList [(Node 1, 5), (Node 2, 2), (Node 4, 1), (Node 7, 22)]),
                (Node 4, Map.fromList [(Node 1, 6), (Node 3, 1), (Node 5, 6)]),
                (Node 5, Map.fromList [(Node 4, 6), (Node 7, 8)]),
                (Node 6, Map.fromList [(Node 2, 7), (Node 7, 10)]),
                (Node 7, Map.fromList [(Node 3, 22), (Node 5, 8), (Node 6, 10)])]

findShortestDistances::Map.Map Origin (Map.Map Destination Cost) -> Origin -> Map.Map Node Distance
findShortestDistances edges origin = calculateOneNode (Map.keys (edges Map.! origin)) (Map.singleton origin 0) origin (Set.singleton origin)
  where calculateOneNode needToVisit shortestDistances currentNode calculated
          | not $ null needToVisit = calculateOneNode (tail needToVisit) newShortestDistances currentNode calculated
          | not $ null possibleNextNodes = calculateOneNode newNeedToVisit shortestDistances nextNode (Set.insert nextNode calculated)
          | otherwise = shortestDistances
          where neighbor = head needToVisit
                newDistance = (edges Map.! currentNode) Map.! neighbor + shortestDistances Map.! currentNode
                newShortestDistances = if neighbor `Map.notMember` shortestDistances || newDistance < shortestDistances Map.! neighbor
                                       then Map.insert neighbor newDistance shortestDistances
                                       else shortestDistances
                possibleNextNodes = Set.toList $ Set.difference (Map.keysSet shortestDistances) calculated
                nextNode = fst $ foldr1 (\x y -> if snd x < snd y then x else y) $ map (\x -> (x, shortestDistances Map.! x)) possibleNextNodes
                newNeedToVisit = filter (`Set.notMember` calculated) (Map.keys (edges Map.! nextNode))
